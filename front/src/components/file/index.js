import React, { Component } from 'react';
import "./index.css";

export default class File extends Component {
    render(){
        const {fileName} = this.props;

        return (
            <div className="file">
                <img className='image' src='/images/file-word.png' alt='1'/>
                <span>{fileName}</span>
            </div>
        );
    }
}