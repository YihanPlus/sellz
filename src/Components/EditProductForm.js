import React from "react"
import {createProduct, updateProduct} from "../utils"
import {Button, Form, Input, message, Modal} from "antd"
import {ScanOutlined, TagOutlined} from "@ant-design/icons"



class EditProductForm extends React.Component {
    state = {
        displayModal: false,
    };

    handleCancel = () => {
        this.setState({
            displayModal: false,
        });
    };

    updateProductOnClick = () => {
        this.setState({
            displayModal: true,
        });
    };

    onFinish = (data) => {
        data.sku = this.props.updateSKU.sku;
        data.userEmail = this.props.userEmail;
        console.log('collect product data from user to be updated -> ', data);
        updateProduct(data)
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
                <Button
                    shape="round"
                    type="primary"
                    style={ {marginTop: 15, width: 150}}
                    onClick={this.updateProductOnClick}
                >
                    Update
                </Button>
                <Modal
                    title="Update New SKU"
                    visible={this.state.displayModal}
                    onCancel={this.handleCancel}
                    footer={null}
                    destroyOnClose={true}
                >
                    <Form
                        name="update_sku"
                        initialValues={{ remember: true }}
                        onFinish={this.onFinish}
                        preserve={false}
                    >
                        <Form.Item
                            name="sku"
                            label="The SKU you are updating"
                        >{this.props.updateSKU.sku}
                        </Form.Item>
                        <Form.Item
                            name="name"
                            label="Product Name"
                            rules={[{ required: true, message: "Please update your product name!" }]}
                        >
                            <Input prefix={<TagOutlined />} placeholder={this.props.updateSKU.name} />
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit">
                                Save Changes
                            </Button>
                        </Form.Item>
                    </Form>
                </Modal>
            </>
        );
    };
}

export default EditProductForm;
