import React, { useEffect, useState } from 'react';

const PublicHome = () => {
  

  useEffect(() => {
    // Hacer una solicitud GET a la API cuando el componente se monta
    fetch('http://localhost:8080/public/home')
      .then(response => {
        if (!response.ok) {
          throw new Error('No se pudo obtener el mensaje.');
        }
        return response.text();
      })
  }, []);

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>Bienvenido a la Página Pública</h1>
    </div>
  );
};

// Estilos en línea para el componente
const styles = {
  container: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    height: '100vh',
    backgroundColor: '#f3f4f6',
    color: '#333',
    textAlign: 'center',
  },
  title: {
    fontSize: '2.5rem',
    fontWeight: 'bold',
    color: '#4a90e2',
    marginBottom: '1rem',
  },

};

export default PublicHome;
