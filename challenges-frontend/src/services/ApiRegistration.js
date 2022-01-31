const SERVER_URL = 'http://localhost:8080/app/registration';

class ApiRegistration {

    sendRegistration(firstName,
                     lastName,
                     email,
                     password,
                     role) {
        return fetch(SERVER_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName,
                lastName,
                email,
                password,
                role
            })
        });
    }
}

export default new ApiRegistration();