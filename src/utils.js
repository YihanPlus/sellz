export const signin = (credential) => {
    const { email, password } = credential;
    const signinUrl = `/signin?email=${email}&password=${password}`;

    return fetch(signinUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
    }).then((response) => {
        console.log(response);
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to sign in");
        }
    });
};

export const signup = (data) => {
    const signupUrl = "/signup";

    return fetch(signupUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to sign up");
        }
    });
};

export const getProducts = ( userInfo ) => {
    const productsUrl = `/products/${userInfo}/product`;
    console.log('this is user ID -> ', userInfo)
    return fetch(productsUrl).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get product list");
        }
        return response.json();
    });
};

export const getProduct = (productId) => {
    return fetch(`/${productId}`).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get product");
        }
        return response.json();
    });
};

export const createProduct = (data) => {
    const createProductUrl = `/create`;
    return fetch(createProductUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to add new sku");
        }
    });
}

export const deleteProduct = (data) => {
    console.log("data passing when call deleteProduct -> ", data);
    const deleteProductUrl = `/delete`;
    return fetch(deleteProductUrl, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to add new sku");
        }
    });
}

export const updateProduct = (data) => {
    console.log("data passing when call updateProduct -> ", data);
    const updateProductUrl = `/update`;
    return fetch(updateProductUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to update sku");
        }
    });
}