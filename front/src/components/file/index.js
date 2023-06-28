import React, { Component } from 'react';

export default class File extends Component {
    render(){
        const {fileName} = this.props;

        return (
            <div className="file">
                <img src='/file-word.png' alt='1'/>
                <span>{fileName}</span>
            </div>
        );
    }
}