import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginForm from './componentes/LoginForm';
import EmployeeForm from './componentes/EmployeeForm';
import PublicHome from './componentes/PublicHome';



function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        <Route path="/register/empleados/create" element={<EmployeeForm />} />
        <Route path="/public/home" element={<PublicHome />} />
        {/* Otras rutas */}
        
      </Routes>
    </Router>
  );
}

export default App;
