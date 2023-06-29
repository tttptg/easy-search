import React, {Component} from 'react';
import './index.css';
import { useHistory,withRouter } from 'react-router-dom';

class SelectRoot extends Component {


    handleStart = ()=>{
        // const history = useHistory();
        // history.push("/result")
        // const navigate = useNavigate();
        // navigate("/result");
        this.props.history.push('/result');
        // const currentPath = window.location.pathname;
        // const resultPath = `http://localhost:3000/result`;
        // window.location.href = resultPath;
        // window.location.href = '/result';
        this.props.handleChooseRoot(this.props.nowPath);
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