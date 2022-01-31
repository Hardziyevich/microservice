import * as React from "react";
import SetData from "../services/SetData";
import ApiUserRecord from "../services/ApiUserRecord";
import FieldUserData from "../services/FieldUserData";

class RegistrationOrder extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            service: [],
            serviceType: '',
            serviceButtonState: true,
            days: [],
            day: '',
            dayButtonState: true,
            groomers: [],
            groomer: '',
            groomerButtonState: true,
            times: [],
            timeType: '',
            timeButtonState: true,
//user
            firstName: '',
            lastName: '',
            email: '',
            petName: '',
            firstNameError: '',
            lastNameError: '',
            emailError: '',
            petNameError: '',
            id: 0,
            orderState: false
        };

        this.onSetServiceType = this.onSetServiceType.bind(this);
        this.onSetGroomer = this.onSetGroomer.bind(this);
        this.onSetDay = this.onSetDay.bind(this);
        this.onSetTimeType = this.onSetTimeType.bind(this);
        this.onResetMainFields = this.onResetMainFields.bind(this);

        this.updateDay = this.updateDay.bind(this);
        this.updateService = this.updateService.bind(this);
        this.updateGroomer = this.updateGroomer.bind(this);
        this.updateFreeTime = this.updateFreeTime.bind(this);

        //user functions

        this.onSetFirstName = this.onSetFirstName.bind(this);
        this.onSetLastName = this.onSetLastName.bind(this);
        this.onSetEmail = this.onSetEmail.bind(this);
        this.onSetPetName = this.onSetPetName.bind(this);

        this.sendRegistration = this.sendRegistration.bind(this);

        //user error

        // this.onSetFirstNameError = this.onSetFirstNameError.bind(this);
        // this.onSetLastNameError = this.onSetLastNameError.bind(this);
        // this.onSetEmailError = this.onSetEmailError.bind(this);
        // this.onSetPetNameError = this.onSetPetNameError.bind(this);

    }

    onSetFirstName(e) {
        this.setState({
            firstName: e.target.value
        })
    }

    onSetLastName(e) {
        this.setState({
            lastName: e.target.value
        })
    }

    onSetEmail(e) {
        this.setState({
            email: e.target.value
        })
    }

    onSetPetName(e) {
        this.setState({
            petName: e.target.value
        })
    }

    onSetTimeType(e) {
        this.setState({
            timeType: e.target.value
        })
    }

    onSetServiceType(e) {
        this.setState({
            serviceType: e.target.value
        })
    }

    onSetDay(e) {
        this.setState({
            day: e.target.value
        })
    }

    onSetGroomer(e) {
        this.setState({
            groomer: e.target.value
        })
    }

    onResetMainFields(e) {
        this.setState({
            serviceType: '',
            day: '',
            groomer: '',
            timeType: ''
        })
    }

    updateDay(e) {
        e.preventDefault();
        if (this.state.dayButtonState) {
            ApiUserRecord.postWorkDay(
                this.state.groomer,
                this.state.serviceType
            ).then(res => {
                if (res.ok) {
                    let temp = [];
                    res.json().then(data => {
                        data.forEach(item => {
                            temp.push(item);
                        });
                        this.setState({
                            days: temp,
                            dayButtonState: false
                        })
                    })
                }
            })
        } else {
            this.setState({
                dayButtonState: true
            })
        }
    }

    updateService(e) {
        e.preventDefault();

        if (this.state.serviceButtonState) {
            ApiUserRecord.postService(
                this.state.groomer,
                this.state.day
            ).then(res => {
                if (res.ok) {
                    let temp = [];
                    res.json().then(data => {
                        data.forEach(item => {
                            temp.push(item);

                        });
                        this.setState({
                            service: temp,
                            serviceButtonState: false
                        })
                    })
                }
            })
        } else {
            this.setState({
                serviceButtonState: true
            })
        }
    }

    updateGroomer(e) {
        e.preventDefault();
        if (this.state.groomerButtonState) {
            ApiUserRecord.postGroomer(
                this.state.serviceType,
                this.state.day
            ).then(res => {
                if (res.ok) {
                    let temp = [];
                    res.json().then(data => {
                        data.forEach(item => {
                            temp.push(item);

                        });
                        this.setState({
                            groomers: temp,
                            groomerButtonState: false
                        })
                    })
                }
            })
        } else {
            this.setState({
                groomerButtonState: true
            })
        }
    }

    updateFreeTime(e) {
        e.preventDefault();
        if (this.state.timeButtonState) {
            ApiUserRecord.postFreeTime(
                this.state.groomer,
                this.state.serviceType,
                this.state.day
            ).then(res => {
                if (res.ok) {
                    let temp = [];
                    res.json().then(data => {
                        data.forEach(item => {
                            temp.push(item);

                        });
                        this.setState({
                            times: temp,
                            timeButtonState: false
                        })
                    })
                }
            })
        } else {
            this.setState({
                timeButtonState: true
            })
        }
    }

    sendRegistration(e) {
        e.preventDefault();
        ApiUserRecord.postRegistration(
            this.state.groomer,
            this.state.serviceType,
            this.state.day,
            this.state.timeType,
            this.state.firstName,
            this.state.lastName,
            this.state.email,
            this.state.petName
        ).then(res => {
            if (res.ok) {
                res.json().then(json => {
                    this.setState({
                        id: json,
                        orderState: true
                    })
                });
            } else {
                res.json().then(json => {
                    this.setState({
                        firstNameError: json.firstNameError,
                        lastNameError: json.lastNameError,
                        emailError: json.emailError,
                        petNameError: json.petNameError
                    });
                });
            }
        });
    }

    render() {
        return (
            <dir>
                <p>Record for services</p>
                <SetData
                    message={"Choose service"}
                    name={"service"}
                    type={this.state.serviceType}
                    update={this.updateService}
                    change={this.onSetServiceType}
                    value={this.state.service}/>
                <SetData
                    message={"Choose day"}
                    name={"day"}
                    type={this.state.day}
                    update={this.updateDay}
                    change={this.onSetDay}
                    value={this.state.days}/>
                <SetData
                    message={"Choose groomer"}
                    name={"groomer"}
                    type={this.state.groomer}
                    update={this.updateGroomer}
                    change={this.onSetGroomer}
                    value={this.state.groomers}/>
                <SetDataCondition
                    message={"Choose time"}
                    name={"time"}
                    type={this.state.timeType}
                    update={this.updateFreeTime}
                    change={this.onSetTimeType}
                    value={this.state.times}
                    groomer={this.state.groomer}
                    day={this.state.day}
                    service={this.state.serviceType}/>
                <Update
                    button={"change record"}
                    action={this.onResetMainFields}
                />
                <FieldUserData
                    condition={this.state.timeType}
                    onRegistration={this.sendRegistration}

                    firstNameValue={"First Name"}
                    firstName={this.state.firstName}
                    onSetFirstName={this.onSetFirstName}
                    valueNameError={this.state.firstNameError}

                    lastNameValue={"Last Name"}
                    lastName={this.state.lastName}
                    onSetLastName={this.onSetLastName}
                    valueLastNameError={this.state.lastNameError}

                    emailsValue={"Email"}
                    email={this.state.email}
                    onSetEmail={this.onSetEmail}
                    valueEmailError={this.state.emailError}

                    petNameValue={"Pet Name"}
                    petName={this.state.petName}
                    onSetPetName={this.onSetPetName}
                    valuePetNameError={this.state.petNameError}
                />
                <Redirect
                    id={this.state.id}
                    state={this.state.orderState}
                />
            </dir>
        )
    }
}

function Redirect(props) {
    return (
        props.id !== 0 &&
        props.state === true &&
        window.location.replace("/successful")
    )
}

function SetDataCondition(props) {
    return (
        (props.groomer && props.day && props.service &&
            <SetData
                message={props.message}
                name={props.name}
                type={props.type}
                update={props.update}
                change={props.change}
                value={props.value}
            />)
    )
}

function Update(props) {
    return (
        <form onSubmit={props.action}>
            <button type={"submit"}>{props.button}</button>
        </form>
    )
}

export default RegistrationOrder;

