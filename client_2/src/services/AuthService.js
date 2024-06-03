import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1';

class AuthService {
    login(username, password) {
        return axios.post(`${API_URL}/login`, {
            username,
            password
        });
    }

    logout() {
        localStorage.removeItem('token');
    }

    isAuthenticated() {
        return !!localStorage.getItem('token');
    }
}

export default new AuthService();
