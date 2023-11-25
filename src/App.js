import logo from './logo.svg';
import './App.css';
import Home from "./components/home/home";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Fondo_Form from './imagenes/Fondo_Form.png';
function App() {
  return (
    <div className="contenedor__todo">
      <div class="caja__trasera">
        <div class="caja__trasera-login">
          <h3>¿Ya tienes una cuenta?</h3>
          <p>Inicia sesión para entrar en la página</p>
          <button id="btn__iniciar-sesion">Iniciar Sesion</button>
        </div>

        <div class="caja__trasera-register">
          <h3>¿Aún no tienes cuenta?</h3>
          <p>Regístrate para que puedas iniciar sesión</p>
          <button id="btn__registrarse">Regístrarse</button>
        </div>
      </div>

      <div class="contenedor__login-register">
        <form action="" class="formulario__login">
          <h2>Iniciar Sesión</h2>
          <input type="text" placeholder='Usuario'></input>
          <input type="password" placeholder="Contraseña"></input>
          <button>Ingresar</button>
        </form>

        <form action="" class="formulario__register">
          <h2>Regístrarse</h2>
          <input type="text" placeholder="Nombre Completo"></input>
          <input type="text" placeholder="Correo electronico"></input>
          <input type="text" placeholder="Usuario"></input>
          <input type="text" placeholder="Contraseña"></input>
          <button>Regístrarse</button>
        </form>
      </div>
      <script>src="components/home"</script>
    </div>
  )
}
export default App;
Home.propTypes = {};
Home.defaultProps = {};

