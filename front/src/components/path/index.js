import React, {Component} from "react";
import "./index.css";

export default class Path extends Component {
    render() {
        const {pathName} = this.props;

        return (
            <div className="path">
                <span>{pathName}</span>
            </div>
        );
    }
}