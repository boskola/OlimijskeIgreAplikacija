import React from "react";
import { Form, Button, Row, Col, Table } from "react-bootstrap";
import OlimpijskeAxios from "../../apis/OlimpijskeAxios";
import {withParams, withNavigation} from '../../routeconf';

class Stats extends React.Component{


constructor(props) {
        super(props);


    this.state = { 
        takmicari: [],
        takmicariStats: []}
        
}

componentDidMount(){
    this.getParoviStats();
}

// async getParoviStats() {    
//     try {
//       let result = await OlimpijskeAxios.get("/takmicari/stats");
//       this.setState({
//          takmicariStats: result.data
//         });

//     } catch (error) {
//       console.log(error);
//     }
// }

async getParoviStats() {    
    try {
      let result = await OlimpijskeAxios.get("/takmicari/stats2");
      this.setState({
         takmicariStats: result.data
        });

    } catch (error) {
      console.log(error);
    }
}

renderStatistika() {
    return this.state.takmicariStats.sort((a, b) => (a.brojMedalja > b.brojMedalja) ? -1 : 1).map((takmicar, index) => {
        return (
           <tr key={takmicar.drzava}>
              <td>{takmicar.drzava}</td>
              <td>{takmicar.brojMedalja}</td>
           </tr>
        );
     })
}

render(){
    return(
        <div>
        <Col>
            <Row><h1>Statistika</h1></Row>
            <Row>
                <Table style={{marginTop:5}}>
                    <thead>
                        <tr>
                            <th>Drzava</th>
                            <th>Broj medalja</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderStatistika()}
                    </tbody>                  
                </Table>
            </Row>
        </Col>
        </div>
    );
}



}


export default withNavigation(withParams(Stats));