import React from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import OlimpijskeAxios from "../../apis/OlimpijskeAxios";
import { withNavigation } from "../../routeconf";

class AddTakmicar extends React.Component{
    constructor(props) {
        super(props);

        let takmicar = {
            imePrezime: "",
            brojMedalja: "",
            datumRodjenja: "",
            drzavaId: 0
        }

        this.state = {takmicar: takmicar, drzave: []};

    }

    componentDidMount(){
        this.getDrzave();
      }

      async getDrzave() {
        try {
          let result = await OlimpijskeAxios.get("/drzave");
          this.setState({ drzave: result.data });
        } catch (error) {
          alert("Could not fetch genres.");
          console.log(error);
        }
      }

    create = () => {
        var params = {

            imePrezime: this.state.takmicar.imePrezime,
            brojMedalja: this.state.takmicar.brojMedalja,
            datumRodjenja: this.state.takmicar.datumRodjenja,
            drzavaId: this.state.takmicar.drzavaId
            
        };

        OlimpijskeAxios.post('/takmicari', params)
        .then(res => {
            // handle success
            console.log(res);
           
            alert('Takmicar was added successfully!');
            this.props.navigate('/takmicari'); 
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again 9999!');
         });
    }

    onInputChange(e) {
        let name = e.target.name;
        let value = e.target.value;

        let takmicar = this.state.takmicar;
        takmicar[name] = value;
    
        this.setState({takmicar: takmicar });
    }

    drzavaSelectionChanged(e){
        let drzavaId = e.target.value;

        let takmicar = this.state.takmicar;
        takmicar.drzavaId = drzavaId;

        this.setState({takmicar: takmicar});
    }


    render(){
        return(
            <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <h1>Dodavanje novog takmicara</h1>

                {/* className="form-control" */}

                <Form>
                    <Form.Group>
                    <Form.Label htmlFor="imePrezime">Ime i prezime</Form.Label>
                    <Form.Control 
                        id="imePrezime"
                        name="imePrezime"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="brojMedalja">Broj medalja</Form.Label>
                    <Form.Control 
                        id="brojMedalja"
                        name="brojMedalja"
                        type="number"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="drzava">Drzava</Form.Label>
                    <Form.Select  name="drzavaId" onChange={event => this.drzavaSelectionChanged(event)}>
                        <option key=""></option>
                        {
                            this.state.drzave.map((drzava) => {
                                return (
                                    <option key={drzava.id} value={drzava.id}>{drzava.naziv}</option>
                                )
                            })
                        }
                    </Form.Select>
                    </Form.Group>

                    <Form.Group>
                    <Form.Label htmlFor="datumRodjenja">Datum rodjenja</Form.Label>
                    <Form.Control 
                        id="datumRodjenja"
                        name="datumRodjenja"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>

                    <Button style={{ marginTop: "25px" }} onClick={(event)=>{this.create(event);}}>
                        Add
                    </Button>
                </Form>
                </Col>
                <Col></Col>
            </Row>
            </>
        )
    }
}

export default withNavigation(AddTakmicar);