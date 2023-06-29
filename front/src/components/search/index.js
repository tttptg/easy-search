import React, {Component} from "react";
import './index.css';

export default class Search extends Component {
    render() {
        return (
            <div id="search-bar">
                <img src="./images/easy-search-logo.png" alt="1" className="small-logo"/>
                <input className='input-bar' type="text" placeholder="搜索框"/>
                <button className='botton-search'>开始检索</button>
            </div>
        );
    }
}