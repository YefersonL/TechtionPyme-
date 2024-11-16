import React, { useState } from 'react';



const EmployeeForm = () => {
  const [name, setName] = useState('');
  const [identification, setIdentification] = useState('');
  const [salaryBase, setSalaryBase] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/v1/register/empleados/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        //credentials: "include",  // Asegura que las credenciales sean enviadas
        body: JSON.stringify({
          nombre: name,
          identificacion: identification,
          salarioBase: salaryBase,
        }),
      });

      if (response.ok) {
        // Resetear los campos del formulario
        setName('');
        setIdentification('');
        setSalaryBase('');

        alert('Empleado registrado exitosamente');
      } else {
        alert('Error al registrar el empleado');
      }
    } catch (error) {
      console.error('Error al registrar el empleado:', error);
      alert('Error al registrar el empleado');
    }
  };

  return (
    <div style={{ maxWidth: '500px', margin: 'auto', padding: '30px', backgroundColor: '#f9fafb', borderRadius: '10px', boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)' }}>
      <h2 style={{ textAlign: 'center', fontSize: '2rem', color: '#1D4ED8', marginBottom: '20px' }}>Registro de Empleado</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '20px' }}>
          <label htmlFor="name" style={{ display: 'block', fontSize: '1rem', color: '#374151', marginBottom: '8px' }}>Nombre</label>
          <input
            type="text"
            id="name"
            style={{
              width: '100%',
              padding: '10px',
              border: '1px solid #D1D5DB',
              borderRadius: '8px',
              fontSize: '1rem',
              outline: 'none',
              boxSizing: 'border-box'
            }}
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div style={{ marginBottom: '20px' }}>
          <label htmlFor="identification" style={{ display: 'block', fontSize: '1rem', color: '#374151', marginBottom: '8px' }}>Identificación</label>
          <input
            type="text"
            id="identification"
            style={{
              width: '100%',
              padding: '10px',
              border: '1px solid #D1D5DB',
              borderRadius: '8px',
              fontSize: '1rem',
              outline: 'none',
              boxSizing: 'border-box'
            }}
            value={identification}
            onChange={(e) => setIdentification(e.target.value)}
            required
          />
        </div>
        <div style={{ marginBottom: '20px' }}>
          <label htmlFor="salaryBase" style={{ display: 'block', fontSize: '1rem', color: '#374151', marginBottom: '8px' }}>Salario Base</label>
          <input
            type="number"
            id="salaryBase"
            style={{
              width: '100%',
              padding: '10px',
              border: '1px solid #D1D5DB',
              borderRadius: '8px',
              fontSize: '1rem',
              outline: 'none',
              boxSizing: 'border-box'
            }}
            value={salaryBase}
            onChange={(e) => setSalaryBase(e.target.value)}
            required
          />
        </div>
        <button
          type="submit"
          style={{
            width: '100%',
            padding: '12px',
            backgroundColor: '#3B82F6',
            color: '#FFFFFF',
            fontSize: '1rem',
            fontWeight: '600',
            borderRadius: '8px',
            border: 'none',
            cursor: 'pointer',
            transition: 'background-color 0.3s'
          }}
          onMouseEnter={(e) => e.target.style.backgroundColor = '#2563EB'}
          onMouseLeave={(e) => e.target.style.backgroundColor = '#3B82F6'}
        >
          Registrar Empleado
        </button>
      </form>
    </div>
  );
};

export default EmployeeForm;
