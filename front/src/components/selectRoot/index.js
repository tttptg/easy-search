import React, {Component} from 'react';
import './index.css';
import { useHistory,withRouter } from 'react-router-dom';
import axios from 'axios';

class SelectRoot extends Component {


    handleStart = ()=>{
        // const history = useHistory();
        // history.push("/result")
        // const navigate = useNavigate();
        // navigate("/result");
        // this.props.history.push('/result');
        // const currentPath = window.location.pathname;
        // const resultPath = `http://localhost:3000/result`;
        // window.location.href = resultPath;
        // window.location.href = '/result';
        // this.props.handleChooseRoot(this.props.nowPath);
        axios.get('http://localhost:8080/es/beginLoad', {
            params: {
                targetPath: this.props.nowPath
            }
        }).then(response => {
            // 处理请求成功的返回结果
            // 执行相应的操作
            this.props.history.push('/result');
            this.props.handleChooseRoot(this.props.nowPath);
        }).catch(error => {
            // 处理请求失败的情况
            console.error(error);
        });

    }

    render() {
        const {nowPath} = this.props;

        return (
            <div className='bar'>
                <span className='span-text'>目标文件夹路径：</span>
                <input className='input' type="text" placeholder="选择路径" value={nowPath === "root"?"/":nowPath}/>
                <button className='botton' onClick={this.handleStart}>开始检索</button>
            </div>
        );
    }
}

export default withRouter(SelectRoot);