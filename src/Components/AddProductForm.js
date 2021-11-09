import React from "react"
import {createProduct} from "../utils"
import {Button, Form, Input, message, Modal} from "antd"
import {ScanOutlined, TagOutlined} from "@ant-design/icons"

class AddProductForm extends React.Component {
    state = {
        displayModal: false,
    };

    handleCancel = () => {
        this.setState({
            displayModal: false,
        });
    };

    addProductOnClick = () => {
        this.setState({
            displayModal: true,
        });
    };

    onFinish = (data) => {
        console.log('collect product data from user -> ', data);
        data.userEmail = this.props.userInfo;
        createProduct(data)
            .then(() => {
                this.setState({
                    displayModal: false,
                });
                message.success(`SKU successfully created!`);
            })
            .catch((err) => {
                message.error(err.message);
            });
    };

    render = () => {
        return (
            <>
                <Button shape="round" type="primary" onClick={this.addProductOnClick}>
                    Add Product
                </Button>
                <Modal
                    title="Add New SKU"
                    visible={this.state.displayModal}
                    onCancel={this.handleCancel}
                    footer={null}
                    destroyOnClose={true}
                >
                    <Form
                        name="normal_sku"
                        initialValues={{ remember: true }}
                        onFinish={this.onFinish}
                        preserve={false}
                    >
                        <Form.Item
                            name="name"
                            rules={[{ required: true, message: "Please input your product name!" }]}
                        >
                            <Input prefix={<TagOutlined />} placeholder="Product Name" />
                        </Form.Item>
                        <Form.Item
                            name="sku"
                            rules={[
                                { required: true, message: "Please input your SKU!" },
                            ]}
                        >
                            <Input prefix={<ScanOutlined />} placeholder="SKU" />
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit">
                                Add Product
                            </Button>
                        </Form.Item>
                    </Form>
                </Modal>
            </>
        );
    };
}

export default AddProductForm;
