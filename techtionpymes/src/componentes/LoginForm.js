import React, { useState } from "react";
import axios from "axios";

const LoginForm = () => {
  const [nombre, setNombre] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/login", 
        { nombre, password },
        { headers: { "Content-Type": "application/json" }}
      );

      if (response.status === 200) {
        // En caso de éxito, redirige a la página principal
        window.location.href = "/empleados";
      }
    } catch (error) {
      // Si el login falla, muestra el mensaje de error
      // Mostrar un mensaje de error que incluye las credenciales
      setErrorMessage(`Credenciales incorrectas. Usuario: ${nombre}, Contraseña: ${password}`);
      console.error("Error en el login:", error);
    }
  };

  return (
    <div>
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nombre de usuario:</label>
          <input 
            type="text" 
            value={nombre} 
            onChange={(e) => setNombre(e.target.value)} 
            required 
          />
        </div>
        <div>
          <label>Contraseña:</label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            required 
          />
        </div>
        <button type="submit">Iniciar sesión</button>
      </form>
      {errorMessage && <p>{errorMessage}</p>}
    </div>
  );
};

export default LoginForm;
