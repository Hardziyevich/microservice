const SERVER_URL = 'http://localhost:8000';
const FIND = '/find'
const SERVICE = '/service'
const FIND_SERVICE = SERVICE + FIND
const WORKDAY = "/day"
const FIND_DAY = WORKDAY + FIND
const GROOMER = '/groomer'
const FIND_GROOMER = GROOMER + FIND
const FIND_FREE_TIME = '/time/freeTime'
const ORDER = '/save/order'

class ApiUserRecord {

    postGroomer(serviceType, day) {
        return fetch(SERVER_URL + FIND_GROOMER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                serviceType,
                day
            })
        })
    }

    postService(groomer, day) {
        return fetch(SERVER_URL + FIND_SERVICE, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                groomer,
                day
            })
        });
    }

    postWorkDay(groomer, serviceType) {
        return fetch(SERVER_URL + FIND_DAY, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                groomer,
                serviceType
            })
        });
    }

    postFreeTime(groomer, serviceType, day) {
        return fetch(SERVER_URL + FIND_FREE_TIME, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                groomer,
                serviceType,
                day
            })
        })
    }

    postRegistration(groomer, serviceType, day, timeType, firstName, lastName, email, petName) {
        return fetch(SERVER_URL + ORDER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                groomer,
                serviceType,
                day,
                timeType,
                firstName,
                lastName,
                email,
                petName
            })
        });
    }
}

export default new ApiUserRecord();
