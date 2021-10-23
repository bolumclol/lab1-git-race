import React from 'react';
import axios from 'axios';
//import '../css/App.css';

const baseURL = 'http://localhost:8080';

class Header extends React.Component{
  render(){
    return(
      <div className="Header">
        <img className="imgLogo" src={'assets/logo.svg'} alt="Logo de la Universidad de Zaragoza"></img>
        <h1 className="title">My first Web App</h1>
      </div> 
    );
  }
}

class Body extends React.Component{
  constructor(props) {
		super(props);
		this.state = {
      message: ''
    };
	}

  getMessage(){
    return new Promise((resolve, reject) => {
        axios.get(baseURL+'/api/message')
        .then(response=>{   //Encuentra los items
            if (response.status === 200) { 
                console.log(response.data);
                resolve(response.data); 
            }else{
                reject(response.status);
            }
        })
    });
  }

	componentDidMount(){
    this.getMessage()
    .then((response) => {
      this.setState({message: response});
    })
    .catch((err) => {
        console.log("Error al buscar el mensaje: "+ err)
    })
    /* OTRA FORMA
    client({method: 'GET', path: '/api/employees'}).done(response => {
			this.setState({employees: response.entity._embedded.employees});
		});
    */
	}

  render(){
    const message = this.state.message;
    return(
      <div className="Body">
        <p className="parag">{message}</p>
      </div>
    );
  }
}

class App extends React.Component{
  render(){
    return(
      <div className="App">
          <Header/>
          <Body/>
      </div>
    );
  }
}

export default App;
