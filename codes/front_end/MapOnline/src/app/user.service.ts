let User: any = {
    loginResult: true,
    username: 'username',
    password: "123",
    userId: 'id',
    imageMessages: ['2', '3', '4'],
    friendMessages: [],
    test: ""
}

export class UserService{
    constructor(){}

    setUser(_user: any){
        User = _user;
    }
    getUser(): any{
        return User;
    }

    setImageMessages(_imageMessages : any){
        User.imageMessages = _imageMessages;
    }

    setTest(_test:any):any{
        User.test = _test;
    }


}