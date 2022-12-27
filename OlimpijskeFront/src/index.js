import React from "react";
import { createRoot } from 'react-dom/client';
import { Route, Link, HashRouter as Router, Routes, Navigate } from 'react-router-dom';
import { Navbar, Nav, Container, Button} from 'react-bootstrap';
import NotFound from "./components/NotFound";
import Home from "./components/Home";
import Takmicari from "./components/linije/Takmicari";
import AddTakmicar from "./components/linije/AddTakmicar";
import Prijava from "./components/linije/Prijava";
import Login from './components/authorization/Login';
import { logout } from './services/auth';
import Stats from "./components/linije/Stats";


class App extends React.Component {

    render(){
        if(window.localStorage["jwt"]){
        return(
            <div>
                <Router>
                <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            Olimpijske Igre
                        </Navbar.Brand>
                        <Nav>
                        <Nav.Link as={Link} to="/takmicari">
                            Takmicari
                        </Nav.Link>
                        <Button onClick={()=>logout()}>Log out</Button>
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>} />
                        <Route path="/login" element={<Navigate replace to="/takmicari"/>} />
                        <Route path="/takmicari" element={<Takmicari/>}/>
                        <Route path="/takmicari/add" element={<AddTakmicar/>}/>
                        <Route path="/takmicari/prijava/:id" element={<Prijava/>}/>
                        <Route path="/takmicari/stats" element={<Stats/>}/>
                        <Route path="*" element={<NotFound/>} />
                    </Routes>
                    </Container>
                </Router>
            </div>
        )
        }else{
            return(
                <div>
                    <Router>
                    <Navbar expand bg="dark" variant="dark">
                            <Navbar.Brand as={Link} to="/">
                                OI
                            </Navbar.Brand>
                            <Nav>
                            <Nav.Link as={Link} to="/takmicari">
                                Takmicari
                            </Nav.Link>
                            <Nav.Link as={Link} to="/login">
                                Login
                            </Nav.Link>
                            </Nav>
                        </Navbar>
                        <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home/>} />
                            <Route path="/login" element={<Login/>} />
                            <Route path="/takmicari" element={<Takmicari/>}/>
                            <Route path="*" element={<Navigate replace to = "/login"/>}/>
                        </Routes>
                        </Container>
                    </Router>
                </div>
            )
        }
    }

}

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App/>,
);
