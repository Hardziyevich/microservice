import * as React from "react";

class SetData extends React.Component {
    render() {
        return (
            (!this.props.type &&
                <div>
                    <label htmlFor={this.props.name}>{this.props.message}: </label>
                    <SelectOption
                        name={this.props.name}
                        update={this.props.update}
                        change={this.props.change}
                        value={this.props.value}/>
                </div>) ||
            (this.props.type && <div>
                <label htmlFor={this.props.name}>{this.props.message}: {this.props.type}</label>
            </div>)
        )
    }
}

function SelectOption(props) {

    return (
        <select
            onClick={props.update}
            onClickCapture={props.change}
            name={props.name}
        >
            <SetOption value={props.value}/>
        </select>
    )
}

function SetOption(props) {
    const elements = props.value;
    return elements.map((element) => (
        <option key={element}>{element}</option>
    ))
}

export default SetData;