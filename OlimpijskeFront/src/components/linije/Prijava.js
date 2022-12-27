import React from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import OlimpijskeAxios from "../../apis/OlimpijskeAxios";
import {withParams, withNavigation} from '../../routeconf';

class Prijava extends React.Component{

    constructor(props) {
        super(props);
        
        this.state = {
            
            disciplina: ""
            };
            
    }

    prijava(){
        OlimpijskeAxios.post(window.location.href.split('#')[1] + "/" + this.state.disciplina)
        .then(res => {
            // handle success
         
            console.log(res);
            alert('Prijava was successfull!');
            this.props.navigate('/takmicari');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again 123!');
         });
    }

    onInputChange(e) {
        let name = e.target.name;
        let value = e.target.value;
        console.log(value);
    
        this.setState((state, props) => ({
            [name]: value
          }));
    }

    render(){
        return(
            <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                <Row><h1>Prijava</h1></Row>
                <Form>
                    <Form.Group>
                    <Form.Label htmlFor="disciplina">Disciplina</Form.Label>
                    <Form.Control 
                        id="disciplina"
                        name="disciplina"
                        type="text"
                        onChange={(e) => this.onInputChange(e)}/>
                    </Form.Group>
                    <Button style={{ marginTop: "25px" }} onClick={(event)=>{this.prijava(event);}}>
                        Izvrsi prijavu
                    </Button>
                </Form>
                </Col>
                <Col></Col>
            </Row>
            </>
        )
    }


}

export default withNavigation(withParams(Prijava));