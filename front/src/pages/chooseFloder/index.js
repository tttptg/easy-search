import React, { Component } from 'react';
import "./index.css"
import PathShow from '../../components/pathShow';
import FloderList from '../../components/folderList';
import SelectRoot from '../../components/selectRoot';

export default class ChooseFloder extends Component {
    state = {
        nowPath:"root"
    };

    handleNowPath = (newPath)=>{
        console.log("handleNowPath")
        console.log(newPath)
        this.setState({
            nowPath:newPath
        })
    }

    handleBack = ()=>{
        const {nowPath} = this.state;
        let newPath;
        if(nowPath !== "root"){
            const paths = nowPath.split('/');
            console.log(paths);
            if(paths.length === 2 && paths[1] === ""){
                newPath = "root";
            }else if(paths.length === 2){
                newPath = paths[0]+"/";
            }else{
                paths.pop();
                newPath = paths.join("/");
            }
            console.log(newPath);
            this.setState({
                nowPath:newPath
            })
        }
    }

    render() {
        const {nowPath} = this.state;

        return (
            <div className='contains'>
                <div className='top-bar'>
                    <img src='/images/easy-search-logo.png' className='logo' alt='1'/>
                </div>
                <div className='path-bar'>
                    <img src='/images/arrowup.png' alt='1' className='up-icon' onClick={this.handleBack}/>
                    <PathShow nowPath={nowPath}/>
                </div>
                <div className='center' >
                    <FloderList nowPath={nowPath} handleNowPath={this.handleNowPath.bind(this)}/>
                </div>
                <div className='bottom'>
                    <SelectRoot nowPath={nowPath}/>
                </div>
            </div>
        )
    }
}