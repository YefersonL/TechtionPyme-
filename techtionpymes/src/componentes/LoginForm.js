import React, { useState } from "react";

const LoginForm = () => {
  const [nombre, setNombre] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new URLSearchParams();
    formData.append("nombre", nombre);
    formData.append("password", password);

    try {
      const response = await fetch(
        "http://localhost:8080/v1/login",  // Aquí envías el parámetro continue
        {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: formData,
        }
      );

      if (response.ok) {
        const data = await response.json();
        const { token, role, continueUrl } = data; // Suponiendo que 'continueUrl' es la URL de redirección

        // Almacena el token de autenticación en el localStorage
        localStorage.setItem("authToken", token);

        // Si se pasa una URL de continuación, redirige a esa URL
        if (continueUrl) {
          window.location.href = continueUrl;
        } else {
          // Si no se pasa ninguna URL de continuación, redirige al inicio
          window.location.href = "/register/empleados/create";
        }
      } else if (response.status === 401) {
        setErrorMessage("Credenciales incorrectas.");
      } else if (response.status === 500) {
        setErrorMessage("Hubo un error en el servidor. Intenta más tarde.");
      } else {
        setErrorMessage("Hubo un error al intentar iniciar sesión. Intenta nuevamente.");
      }
    } catch (error) {
      setErrorMessage("Hubo un error al intentar iniciar sesión. Intenta nuevamente.");
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
