import React, {Component} from "react";
import "./index.css";

export default class Floder extends Component {

    handleNowPath = ()=>{
        const {floderName,handleNowPath,nowPath} = this.props;
        let target;
        if(nowPath === "root"){
            target = floderName;
        }else{
            target = nowPath+"/"+floderName;
        }
        this.props.handleNowPath(target)
    }

    render(){
        const {floderName,handleNowPath,nowPath} = this.props;
        let target;
        if(nowPath === "root"){
            target = floderName;
        }else{
            target = nowPath+"/"+floderName;
        }

        return (
            <div className="floder" onClick={this.handleNowPath}>
                <img className='image' src='/images/floder.png' alt='1'/>
                <span>{floderName}</span>
            </div>
        );
    }
}