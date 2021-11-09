import {Button, Card, List, message} from "antd";
import React, {useEffect, useState} from "react";
import {deleteProduct, getProducts} from "../utils";
import {EditOutlined, DeleteOutlined} from '@ant-design/icons';
import EditProductForm from "./EditProductForm"

const ProductList = (props) => {
        const [products, setProducts] = useState([]);
        const [loading, setLoading] = useState(false);
        const {userInfo} = props;
        console.log('this is userInfo from App -> ', userInfo);

        useEffect(() => {
            setLoading(true);
            getProducts(userInfo)
                .then((data) => {
                    console.log('product data -> ', data);
                    setProducts(data);
                })
                .catch((err) => {
                    message.error(err.message);
                })
                .finally(() => {
                    setLoading(false);
                });
        }, []);

        const onDeleteProduct = (data) => {
            console.log("data from product list -> ", data);
            deleteProduct(data)
                .then(() => {
                    message.success(`Successfully delete product`);
                })
                .catch((err) => {
                    message.error(err.message);
                });
        };

        //
        // const onEditProduct = (data) => {
        //     console.log('hello from edit');
        //     return (
        //         <EditProductForm product={data} />
        //     )
        // }

        return (
            <div>
                <List
                    style={{marginTop: 20}}
                    pagination={{
                        onChange: page => {
                            console.log(page);
                        },
                        pageSize: 3,
                    }}
                    loading={loading}
                    grid={{
                        gutter: 16,
                        xs: 1,
                        sm: 2,
                        md: 4,
                        lg: 4,
                        xl: 3,
                        xxl: 3,
                    }}
                    dataSource={products}
                    renderItem={(item) => (
                        <List.Item>
                            <Card
                                title={item.name}
                                bordered={false}
                                style={{width: 400, height: 500}}
                                // actions={[
                                //     <EditOutlined
                                //         key="edit"
                                //         onClick={() => {
                                //             onEditProduct(item)
                                //         }}
                                //     />,
                                //     <DeleteOutlined
                                //         key="delete"
                                //         onClick={() => {
                                //             onDeleteProduct(item)
                                //         }}
                                //     />
                                // ]}
                            >
                                <p>{`SKU: ${item.sku}`}</p>
                                <p>{`Create Date: ${(new Date(item.createDate)).toLocaleString()}`}</p>
                                <p>{`Lowest Price: $${item.lowestPrice}`}</p>
                                <List
                                    bordered
                                    dataSource={item.sellerList}
                                    pagination={{
                                        onChange: page => {
                                            console.log(page);
                                        },
                                        pageSize: 2,
                                    }}
                                    style={{height: 250}}
                                    renderItem={seller => (
                                        <List.Item>
                                            <List.Item.Meta
                                                title={seller.name}
                                                description={`Price: $${seller.price}`}
                                            />
                                            {`Inventory Amount: ${seller.inventoryAmount}`}
                                        </List.Item>
                                    )}
                                />
                                <EditProductForm
                                    updateSKU={item}
                                    userEmail={userInfo}
                                />
                                <Button shape="round" type="primary"
                                        style={{
                                            marginTop:15,
                                            float: "right",
                                            width: 150
                                        }}
                                        onClick={ () => {onDeleteProduct(item)} }>
                                    Delete
                                </Button>
                            </Card>

                        </List.Item>

                    )}
                />
            </div>
        );
    }
;

export default ProductList;
