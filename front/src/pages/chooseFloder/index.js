import React, { Component } from 'react';
import "./index.css"
import PathShow from '../../components/pathShow';
import FloderList from '../../components/folderList';
import SelectRoot from '../../components/selectRoot';

export default class ChooseFloder extends Component {
    render() {
        return (
            <div>
                <div className='top-bar'>
                    <img src='/images/easy-search-logo.png' className='logo' alt='1'/>
                </div>
                <div className='path-bar'>
                    <img src='/images/arrowup.png' alt='1' className='up-icon'/>
                    <PathShow/>
                </div>
                <div className='center' >
                    <FloderList/>
                </div>
                <div className='bottom'>
                    <SelectRoot/>
                </div>
            </div>
        )
    }
}