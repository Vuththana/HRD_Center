export class User {
    username;
    email;
    password;

    constructor(_name, _email) {
        this.name = _name;
        this.email = _email;
    }

    setPassword(_password) {
        this.password = _password;
    }
}