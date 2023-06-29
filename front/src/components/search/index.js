import React, {Component} from "react";
import './index.css';
import axios from "axios";

export default class Search extends Component {
    state = {
        inputValue:"",
    }


    handleChangeInputValue = (event)=>{
        console.log(event.target.value);
        this.setState({
            inputValue:event.target.value
        })
    }

    handleSearch = ()=>{
        axios.post("http://localhost:8080/es/search?keyword="+this.state.inputValue).then(response =>{
            // console.log(response.data)
            this.props.handleSearchResult(response.data)
        })
    }

    render() {

        return (
            <div id="search-bar">
                <img src="./images/easy-search-logo.png" alt="1" className="small-logo"/>
                <input className='input-bar' type="text" placeholder="搜索框" onChange={this.handleChangeInputValue}/>
                <button className='botton-search' onClick={this.handleSearch}>开始检索</button>
            </div>
        );
    }
}