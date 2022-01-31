import * as React from "react";
import ApiRegistration from "../services/ApiRegistration";

class RegComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            role: '',
            firstNameError: '',
            lastNameError: '',
            emailError: '',
            passwordError: '',
            roleError: '',
            userExist: false,
            successful: false
        };
        this.handleRegistration = this.handleRegistration.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangeUserPassword = this.onChangeUserPassword.bind(this);
        this.onChangeRole = this.onChangeRole.bind(this);
    }

    onChangeFirstName(e) {
        this.setState({
            firstName: e.target.value
        })
    }

    onChangeLastName(e) {
        this.setState({
            lastName: e.target.value
        })
    }

    onChangeEmail(e) {
        this.setState({
            email: e.target.value
        })
    }

    onChangeUserPassword(e) {
        this.setState({
            password: e.target.value
        })
    }

    onChangeRole(e) {
        this.setState({
            role: e.target.value
        })
    }

    onUserExist() {

    }

    handleRegistration(event) {
        event.preventDefault();
        ApiRegistration.sendRegistration(
            this.state.firstName,
            this.state.lastName,
            this.state.email,
            this.state.password,
            this.state.role
        ).then(resp => {
            if (resp.ok) {
                resp.json().then(
                    json => {
                        this.setState({
                            id: json.id,
                            successful: true
                        });
                    }
                );
                window.location.replace("/");
            } else {
                resp.json().then(json => {
                    this.setState({
                        firstNameError: json.firstNameError,
                        lastNameError: json.lastNameError,
                        emailError: json.emailError,
                        passwordError: json.passwordError,
                        roleError: json.roleError,
                        userExist: json.userExist,
                        successful: false
                    });
                });
            }
        });
        if (this.state.successful === true) {
            window.location.replace("/");
        }
    }

    render() {
        return (
            <form onSubmit={this.handleRegistration}>
                <div>
                    <label htmlFor="firstName">First Name:</label>
                    <input type="text" name="firstName"
                           value={this.state.firstName}
                           onChange={this.onChangeFirstName}/>
                    <label>{this.state.firstNameError}</label>
                </div>
                <div>
                    <label htmlFor="lastName">Last Name:</label>
                    <input type="text"
                           name="lastName"
                           value={this.state.lastName}
                           onChange={this.onChangeLastName}/>
                    <label>{this.state.lastNameError}</label>
                </div>
                <div>
                    <label htmlFor="email">Email:</label>
                    <input type="text"
                           name="email"
                           value={this.state.email}
                           onChange={this.onChangeEmail}/>
                    <label>{this.state.emailError}</label>
                </div>
                <div>
                    <label htmlFor="password">Password:</label>
                    <input type="password"
                           name="password"
                           value={this.state.password}
                           onChange={this.onChangeUserPassword}/>
                    <label>{this.state.passwordError}</label>
                </div>
                <div>
                    <label htmlFor="text">Role:</label>
                    <input type="text"
                           name="role"
                           value={this.state.role}
                           onChange={this.onChangeRole}/>
                    <label>{this.state.roleError}</label>
                </div>
                <AddException available={this.state.userExist} message={'User is exist'}/>
                <button type="submit">registration</button>
            </form>
        )
    }
}

const AddException = ({available,message}) => {
    if (!available) return null;
    return (
        <div>
            <label>{message}</label>
        </div>
    );
};

export default RegComponent;