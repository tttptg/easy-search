import React, {Component} from 'react';
import './index.css';

export default class SelectRoot extends Component {
    render() {
        return (
            <div className='bar'>
                <span className='span'>目标文件夹路径：</span>
                <input className='input-bar' type="text" placeholder="选择路径" value={"D://PC/Downloads/details"} />
                <button className='botton'>开始检索</button>
            </div>
        );
    }
}