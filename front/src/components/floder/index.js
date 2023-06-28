import React, {Component} from "react";
import "./index.css";

export default class Floder extends Component {
    render(){
        const {floderName} = this.props;

        return (
            <div className="floder">
                <img className='image' src='/images/floder.png' alt='1'/>
                <span>{floderName}</span>
            </div>
        );
    }
}