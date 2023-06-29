import React, { Component } from "react";
import "./index.css"
import File from "../file";
import Floder from "../floder";
import axios from "axios";
import ajax from "../../api/ajax";

export default class FloderList extends Component {

    isup = false

    state = {
        data: []
    }

    getSubDir = async (targetName) => {
        const response = await ajax("http://localhost:8080/path/subdirectory", { targetPath: targetName })
        const data = response.data;
        let updatedData;
        // console.log(data);
        if(data !== null){
            updatedData = data.map(item => {
                const updatedPath = item.path.replace(/\\/g, "/");
                return { ...item, path: updatedPath };
            });
        }else{
            updatedData = [];
        }
        // console.log(updatedData)
        this.setState({ data: updatedData });
    }

    componentDidMount() {
        if (this.isup === false) {
            const { nowPath } = this.props;
            this.getSubDir(nowPath);
            // console.log("list-comdidmout")
            this.isup = true
        } else {
            this.isup = false
        }
    }

    getSnapshotBeforeUpdate(prevProps, prevState) {
        if (this.isup === false) {
            const { nowPath } = this.props;
            this.getSubDir(nowPath);
            // console.log("getSnapshotBeforeUpdate")
            this.isup = true
        } else {
            this.isup = false
        }
    }

    // test = ()=>{
    //     this.props.handleNowPath("test")
    //     console.log("test")
    // }


    render() {
        const { nowPath } = this.props;
        const { data } = this.state;


        // this.test();

        return (
            <div className="list">
                {data.map(item => {
                    if (item.type === false) {
                        return <File key={item.path} fileName={item.path} />;
                    } else if (item.type === true) {
                        return <Floder key={item.path} floderName={item.path} handleNowPath={this.props.handleNowPath} nowPath={nowPath} />;
                    }
                    return null;
                })}
            </div>
        );
    }

}