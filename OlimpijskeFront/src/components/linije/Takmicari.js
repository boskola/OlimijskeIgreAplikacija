import React from "react";
import { Row, Col, Button, Table, Form} from "react-bootstrap";
import OlimpijskeAxios from "../../apis/OlimpijskeAxios";
import {withNavigation} from '../../routeconf'

class Takmicari extends React.Component{
    constructor(props){
        super(props);

        const search = {
            drzavaId: "",
            minBrojMedalja: "",
            maxBrojMedalja: ""
        }

        this.state = { 
                    takmicari: [],
                    drzave: [], 
                    search: search, 
                    pageNo: 0, 
                    hideSearch: true}
    }

    componentDidMount(){
        this.getTakmicari(0);   
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

    async getTakmicari(newPageNo) {    
        const conf = {
          params : {
            pageNo: newPageNo,
            drzavaId: this.state.search.drzavaId,
            minMedalja: this.state.search.minBrojMedalja,
            maxMedalja: this.state.search.maxBrojMedalja
          }
        }
        try {
          let result = await OlimpijskeAxios.get("/takmicari", conf);
          console.log(result.headers["total-pages"])
          console.log(result)
          this.setState({
             takmicari: result.data,
             pageNo: newPageNo,
             totalPages: result.headers["total-pages"]
            });

        } catch (error) {
          console.log(error);
        }
    }

    goNext() {
          this.getTakmicari(this.state.pageNo+1)
    }
    
    goPrevious() {
          this.getTakmicari(this.state.pageNo-1)    
    }

    renderTakmicare() {
        return this.state.takmicari.map((takmicar, index) => {
            return (
               <tr key={takmicar.id}>
                  <td>{takmicar.imePrezime}</td>
                  <td>{takmicar.drzavaNaziv}</td>
                  <td>{takmicar.datumRodjenja}</td> 
                  <td>{takmicar.brojMedalja}</td>          
                  {window.localStorage['role'] == 'ROLE_ADMIN' ?
                        [<td key= {takmicar.id+"prijava"}><Button variant="success" style={{ width: "80px", height: "40px"}} onClick={() => this.prijava(takmicar.id)}>Prijava</Button></td>,
                  <td key= {takmicar.id+"delete"}><Button variant="danger" style={{ width: "80px", height: "40px"}} onClick={() => this.delete(takmicar.id)}>Delete</Button></td>]
                  : null}
               </tr>
            );
         })
    }
    
    deleteFromState(takmicarId) {
        var takmicari = this.state.takmicari;
        takmicari.forEach((element, index) => {
            if (element.id === takmicarId) {
                takmicari.splice(index, 1);
                this.setState({takmicari: takmicari});
            }
        });
    }

    delete(takmicarId) {
        OlimpijskeAxios.delete('/takmicari/' + takmicarId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Takmicar was deleted successfully!');
            window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    goToAdd() {
        this.props.navigate('/takmicari/add');  
    }

    goToStats() {
        this.props.navigate('/takmicari/stats');  
    }

    prijava(takmicarId){
        this.props.navigate('/takmicari/prijava/' + takmicarId);
    }

    onInputChange(event) {
        const name = event.target.name;
        const value = event.target.value

        let search = this.state.search;
        search[name] = value;

        console.log(event.target.value);
        this.setState({ search })
        this.getTakmicari(0);
    }

    drzavaSelectionChanged(e){
        const name = e.target.name;
        const value = e.target.value

        let search = this.state.search;
        search[name] = value; 
        this.setState({ search })
        this.getTakmicari(0);
    }

    renderSearchForm() {
        return (
            <>
            <Form style={{ width: "100%" }}>
                <Row><Col>
                    <Form.Group>
                        <Form.Label>Min medalja</Form.Label>
                        <Form.Control
                            name="minBrojMedalja"
                            as="input"
                            type="number"
                            onChange={(e) => this.onInputChange(e)}></Form.Control>
                    </Form.Group>
                </Col></Row>

                <Row><Col>
                    <Form.Group>
                        <Form.Label>Max medalja</Form.Label>
                        <Form.Control
                            name="maxBrojMedalja"
                            as="input"
                            type="number"
                            onChange={(e) => this.onInputChange(e)}></Form.Control>
                    </Form.Group>
                </Col></Row>
                

                <Row>
                    <Col>
                    <Form.Label htmlFor="drzavaId">Drzava</Form.Label>
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
                    </Col>
                </Row>
            </Form>
            <Row><Col>
                <Button className="mt-3" onClick={() => this.getTakmicari(0)}>Search</Button>
            </Col></Row>
            </>
        );
    }
   

    render(){
        return(
            <div>
            <Col>
                <Row><h1>Takmicari</h1></Row>
                <div>
                    <label>
                    <input type="checkbox" onChange={()=>this.setState({hideSearch: !this.state.hideSearch})}/>
                        Prikazi pretragu
                    </label>
                </div>
                <Row hidden={this.state.hideSearch} >
                    {this.renderSearchForm()}
                </Row>
                <br/>
                {window.localStorage['role']=='ROLE_ADMIN'?
                <Row>
                    <Button style={{ width: "80px", height: "40px"}}  onClick={() => this.goToAdd()}>Add</Button>
                </Row>: null}
                  <br/><br/>
                <Row>
                    <Table style={{marginTop:5}}>
                        <thead>
                            <tr>
                                <th>Ime i prezime takmicara</th>
                                <th>Drzava</th>
                                <th>Datum rodjenja</th>
                                <th>Broj osvojenih medalja</th>
                                <th><Button style={{ width: "80px", height: "40px"}}  onClick={() => this.goToStats()}>Stat</Button></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.renderTakmicare()}
                        </tbody>                  
                    </Table>
                    <Button disabled={this.state.pageNo === 0} className="mr-2" style={{ width: "80px", height: "40px"}} onClick={()=>this.goPrevious()}>Prev</Button>
                    <Button disabled={this.state.pageNo===this.state.totalPages-1} style={{ width: "80px", height: "40px"}} onClick={()=>this.goNext()}>Next</Button>
                </Row>
            </Col>
            </div>
        );
    }
}

export default withNavigation(Takmicari);