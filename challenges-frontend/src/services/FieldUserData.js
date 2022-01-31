import * as React from "react";

class FieldUserData extends React.Component {
    render() {
        return (
            this.props.condition && <form onSubmit={this.props.onRegistration}>
                <label>Enter your details</label>
                <Field
                    name={this.props.firstNameValue}
                    value={this.props.firstName}
                    action={this.props.onSetFirstName}
                    valueError={this.props.valueNameError}
                />
                <Field
                    name={this.props.lastNameValue}
                    value={this.props.lastName}
                    action={this.props.onSetLastName}
                    valueError={this.props.valueLastNameError}
                />
                <Field
                    name={this.props.emailsValue}
                    value={this.props.email}
                    action={this.props.onSetEmail}
                    valueError={this.props.valueEmailError}
                />
                <Field
                    name={this.props.petNameValue}
                    value={this.props.petName}
                    action={this.props.onSetPetName}
                    valueError={this.props.valuePetNameError}
                />
                <button type={"submit"}>record</button>
            </form>)
    }
}

function Field(props) {
    return (
        <div>
            <label htmlFor={props.name}>{props.name}: </label>
            <input type="text" name={props.name}
                   value={props.value}
                   onChange={props.action}/>
            <label>{props.valueError}</label>
        </div>
    )
}

export default FieldUserData;