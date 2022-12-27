import OlimpijskeAxios from "../apis/OlimpijskeAxios";
import jwt_decode from "jwt-decode";

export const login = async function(username, password){
    const cred = {
        username : username,
        password : password
    }
    try{
        const ret = await OlimpijskeAxios.post('korisnici/auth', cred)
        window.localStorage.setItem('jwt',ret.data);
        const decoded_jwt = jwt_decode(ret.data)
        window.localStorage.setItem("role", decoded_jwt.role.authority)
    }catch(err){
        console.log(err);
    }
    window.location.reload()
}

export const logout = function(){
    window.localStorage.removeItem("jwt")
    window.localStorage.removeItem("role")
    window.location.reload()
}