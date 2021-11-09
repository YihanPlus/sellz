import {Layout, Typography} from "antd";
import React from "react";
import './App.css';
import SigninForm from "./Components/SigninForm";
import ProductList from "./Components/ProductList"
import SignupForm from "./Components/SignupForm";
import AddProductForm from "./Components/AddProductForm";

const {Header, Content} = Layout;
const {Title} = Typography;

class App extends React.Component {
    constructor() {
        super();
        this.state = {
            authed: false,
            user: ""
        }
    }

    render() {
        const { authed, user } = this.state;

        return (
            <Layout style={{height: "100vh"}}>
                <Header>
                    <div className="header">
                        <Title
                            level={2}
                            style={{color: "white", lineHeight: "inherit", marginBottom: 0}}
                        >
                            Online Seller
                        </Title>
                        <div>
                            { authed
                                ?
                                <AddProductForm userInfo={ user }/>
                                :
                                <SignupForm />
                            }
                        </div>
                    </div>
                </Header>
                <Content
                    style={{
                        padding: "50px",
                        maxHeight: "calc(100% - 64px)",
                        overflowY: "auto",
                    }}
                >
                    { authed ? (
                        <ProductList userInfo={ user }/>
                    ) : (
                        <SigninForm
                            onSuccess={() => this.setState( { authed: true })}
                            recordUser={ this.userHandler }
                        />
                    )}
                </Content>
            </Layout>
        )
    }

    userHandler = (data) => {
        console.log('get user info from signinForm -> ', data);
        this.setState( { user: data.email } );
        console.log(this.state.user);
    }
}

export default App;