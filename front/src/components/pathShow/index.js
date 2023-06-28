import React, {Component} from 'react';
import Path from '../path';
import "./index.css"

export default class PathShow extends Component {
    state = {
        data: ["PC", "Downloads", "details"], // 存储 pathName 的数据链表
    };

    render() {
        const { data } = this.state;

        return (
            <div className="path-container">
                {data.map((pathName, index) => (
                    <React.Fragment key={index}>
                        <Path pathName={pathName} />
                        {index < data.length - 1 && <span className="separator">&gt;</span>}
                    </React.Fragment>
                ))}
            </div>
        );
    }
}