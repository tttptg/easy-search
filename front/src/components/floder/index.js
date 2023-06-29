import React, {Component} from "react";
import "./index.css";

export default class Floder extends Component {

    handleNowPath = ()=>{
        const {floderName,handleNowPath,nowPath} = this.props;
        let target;
        if(nowPath === "root"){
            target = floderName;
        }else if(nowPath.endsWith('/')){
            target = nowPath+floderName;
        }else{
            target = nowPath+"/"+floderName;
        }
        target.replace(/\//g, "/");
        // console.log(target)
        this.props.handleNowPath(target)
    }

    // test = () => {
    //     this.props.handleNowPath("test")
    //     console.log("test")
    // }

    render(){
        // console.log(this.props.handleNowPath)
        // console.log("Floder render")

        const {floderName,nowPath} = this.props;
        // let target;
        // if(nowPath === "root"){
        //     target = floderName;
        // }else{
        //     target = nowPath+"/"+floderName;
        // }

        return (
            <div className="floder" onDoubleClick={this.handleNowPath}>
                <img className='image' src='/images/floder.png' alt='1'/>
                <span>{floderName}</span>
            </div>
        );
    }
}