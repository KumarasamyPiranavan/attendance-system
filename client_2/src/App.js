import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import HomePage from './pages/HomePage';

const App = () => {
    const isLoggedIn = !!localStorage.getItem('token');

    return (
        <Router>
            <Routes>
                <Route path="/login" element={isLoggedIn ? <Navigate to="/home" /> : <LoginPage />} />
                <Route path="/home" element={isLoggedIn ? <HomePage /> : <Navigate to="/login" />} />
                <Route path="*" element={<Navigate to={isLoggedIn ? "/home" : "/login"} />} />
            </Routes>
        </Router>
    );
};

export default App;
