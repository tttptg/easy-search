import React, {Component} from "react";
import Search from "../../components/search";
import PathShow from "../../components/pathShow";
import './index.css';
import { withRouter } from "react-router-dom/cjs/react-router-dom";

class Result extends Component {
    state = {
        nowPath:"root",
        searchResult:""
    }

    backToSelect = ()=>{
        this.props.history.goBack();
    }

    handleSearchResult = (result)=>{
        this.setState({searchResult:result})
    }

    render(){
        const {chooseRoot} = this.props;
        console.log(chooseRoot);
        return (
            <div id="result-bar">
                <div id="top-bar">
                    <Search handleSearchResult={this.handleSearchResult.bind(this)}/>
                </div>
                <div id="path">
                    <PathShow nowPath={chooseRoot}/>
                    <img src="/images/rollback.png" alt="1"  className="rollback" onClick={this.backToSelect}/>
                </div>
                <div id="center-bar">
                    <div id="download-bar">
                        <span>检索结果：</span>
                        <button className="downloads-button">下载检索结果</button>
                    </div>
                    <div className="result" dangerouslySetInnerHTML={{ __html: this.state.searchResult }}>
                        {/* {this.state.searchResult === "" ? "等待查询中":this.state.searchResult} */}
                    </div>
                </div>
                
            </div>
        )
    }
}

export default withRouter(Result);