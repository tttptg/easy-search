import React, {Children, Component} from "react";
import Search from "../../components/search";
import PathShow from "../../components/pathShow";
import './index.css';
import { withRouter } from "react-router-dom/cjs/react-router-dom";
import { tab } from "@testing-library/user-event/dist/tab";
import { saveAs } from 'file-saver';

class Result extends Component {
    state = {
        nowPath:"root",
        searchResult:"",
        checked:[]
    }

    backToSelect = ()=>{
        this.props.history.goBack();
    }

    handleSearchResult = (result)=>{
        this.setState({searchResult:result,checked:[]})
    }

    handleCheck = (event) => {
        console.log(this.state.checked);
        const {checked} = event.target;
        if(checked) {
            this.setState({
                checked:[...this.state.checked,event.target.id]
            });
        }else{
            const newChecked = this.state.checked.filter((key) => {return key !== event.target.id;});
            this.setState({
                checked:newChecked
            });
        }
    }

    // 处理下载按钮点击事件
    handleDownload = () => {
        
        const { checked } = this.state;

        // 遍历checked数组
        const labels = checked.map((id) => {
            // 根据id查找对应的元素
            const element = document.querySelector(`#${id}`);
            console.log(element);
            // 获取同级下label的值
            const labelValue = element.nextElementSibling.textContent;
            // 将<br>替换为换行符
            const replacedContent = labelValue
                .replace(/<br>/g, '\r\n')
                .replace(/<strong>/g, '')
                .replace(/<\/strong>/g, '');
            return replacedContent;
        });
        // // 要保存的文本内容
        // let text = '';
        // labels.forEach((item)=>{text = text + item})
        // console.log(text);

        // 创建 Blob 对象
        const blob = new Blob([labels], { type: 'text/plain;charset=utf-8' });

        // 创建下载链接
        const downloadLink = document.createElement('a');
        downloadLink.href = URL.createObjectURL(blob);
        downloadLink.download = 'file.txt';

        // 触发点击下载链接的操作
        downloadLink.click();
    };

    render(){
        const {chooseRoot} = this.props;
        console.log(chooseRoot);
        const resultList = this.state.searchResult.split('<br><br>');
        resultList.pop();
        console.log(resultList);
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
                        <button className="downloads-button" onClick={this.handleDownload}>下载检索结果</button>
                    </div>
                    <div className="result">
                        <ul>
                            {resultList.map((item, index) => (
                                <li key={index} className="one-item">
                                    <input type="checkbox" id={`checkbox${index}`} onChange={this.handleCheck}/>
                                    <label htmlFor={`checkbox${index}`} dangerouslySetInnerHTML={{ __html: item }}></label>
                                </li>
                            ))}
                        </ul>

                    </div>
                </div>
                
            </div>
        )
    }
}

export default withRouter(Result);