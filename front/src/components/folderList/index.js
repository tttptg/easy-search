import React, {Component} from "react";
import "./index.css"
import File from "../file";
import Floder from "../floder";
import axios from "axios";
import ajax from "../../api/ajax";

export default class FloderList extends Component {

    state = {
        data:[]
    }

    getSubDir = async (targetName) => {
        const data = await ajax("http://localhost:8080/path/subdirectory",{targetPath:targetName})
        this.setState({ data: data.data });
    }

    componentDidMount(){
        const {nowPath} = this.props;
        this.getSubDir(nowPath);
    }

    render() {
        const {handleNowPath} = this.props;
        const {data} = this.state;


        return (
            <div className="list">
                {data.map(item => {
                    if (item.type === false) {
                        return <File key={item.path} fileName={item.path} />;
                    } else if (item.type === true) {
                        return <Floder key={item.path} floderName={item.path} />;
                    }
                    return null;
                })}
            </div>
        );
    }

}