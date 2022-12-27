package project.web.controller;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.model.Korisnik;
import project.security.TokenUtils;
import project.service.KorisnikService;
import project.support.KorisnikDtoToKorisnik;
import project.support.KorisnikToKorisnikDto;
import project.web.dto.AuthKorisnikDto;
import project.web.dto.KorisnikDTO;
import project.web.dto.KorisnikPromenaLozinkeDto;
import project.web.dto.KorisnikRegistracijaDTO;

@RestController
@RequestMapping(value = "/api/korisnici", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikDtoToKorisnik toKorisnik;

    @Autowired
    private KorisnikToKorisnikDto toKorisnikDto;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<KorisnikDTO> create(@RequestBody @Validated KorisnikRegistracijaDTO dto){

        if(dto.getId() != null || !dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // KorisnikRegistracijaDTO nasleđuje KorisnikDTO, pa možemo koristiti konverter za njega
        // ostaje da dodatno konvertujemo polje kojeg u njemu nema - password
        Korisnik korisnik = toKorisnik.convert(dto);
//        if(korisnik.getAdresa() == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        // dodatak za zadatak 1
        String encodedPassword = passwordEncoder.encode(dto.getLozinka());
        korisnik.setLozinka(encodedPassword);

        return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDTO> update(@PathVariable Long id, @Valid @RequestBody KorisnikDTO korisnikDTO){

        if(!id.equals(korisnikDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Korisnik korisnik = toKorisnik.convert(korisnikDTO);

//        if(korisnik.getAdresa()  == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(toKorisnikDto.convert(korisnikService.save(korisnik)),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDTO> get(@PathVariable Long id){
        Optional<Korisnik> korisnik = korisnikService.findOne(id);

        if(korisnik.isPresent()) {
            return new ResponseEntity<>(toKorisnikDto.convert(korisnik.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<KorisnikDTO>> get(@RequestParam(defaultValue="0") int page){
        Page<Korisnik> korisnici = korisnikService.findAll(page);
        return new ResponseEntity<>(toKorisnikDto.convert(korisnici.getContent()), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KORISNIK')")
    @RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "promenaLozinke")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody KorisnikPromenaLozinkeDto dto){
        // ova metoda se "okida" kada se primi PUT /korisnici?promenaLozinke
        // pogrešno bi bilo mapirati na npr. PUT /korisnici/lozinke, pošto "lozinka" nije punopravan REST resurs!

        if(!dto.getLozinka().equals(dto.getPonovljenaLozinka())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean rezultat;
        try {
            rezultat = korisnikService.changePassword(id, dto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(rezultat) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/auth", method = RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody AuthKorisnikDto dto) {
        // Perform the authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            // Reload user details so we can generate token
            UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
