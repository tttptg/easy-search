import React, {Component} from "react";
import "./index.css"
import File from "../file";
import Floder from "../floder";

export default class FloderList extends Component {

    render() {

        const data = [
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"Downloads",type:1},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
            {filename:"documents.txt",type:0},
        ];


        return (
            <div className="list">
                {data.map(item => {
                    if (item.type === 0) {
                        return <File key={item.filename} fileName={item.filename} />;
                    } else if (item.type === 1) {
                        return <Floder key={item.filename} floderName={item.filename} />;
                    }
                    return null;
                })}
            </div>
        );
    }

}