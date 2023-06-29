import React, {Component} from "react";
import ChooseFloder from '../chooseFloder';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import Result from "../result";

export default class Routers extends Component{
    state = {
        chooseRoot:"root"
    }

    handleChooseRoot = (value)=>{
        console.log(value)
        this.setState({
            chooseRoot:value
        })
    }

    render(){
        
        return (
            <Router >
                <Switch>
                    <Route path="/" exact render={(props) => <ChooseFloder {...props} handleChooseRoot={this.handleChooseRoot} />} />
                    <Route path="/result" render={(props) => <Result {...props} chooseRoot={this.state.chooseRoot} />} />
                </Switch>
            </Router >
        );
    }
}