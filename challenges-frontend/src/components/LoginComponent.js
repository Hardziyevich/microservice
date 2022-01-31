import * as React from "react";


class LoginComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
        };
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangeUserPassword = this.onChangeUserPassword.bind(this);
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

    handleRegistration(event) {
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleRegistration}>
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
                <button type="submit">login</button>
            </form>
        )
    }
}

export default LoginComponent;