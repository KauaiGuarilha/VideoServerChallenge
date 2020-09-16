package com.videoserverchallenge.model.domain;

import static com.videoserverchallenge.model.domain.ELevelMessage.ERRO;

public enum EMessage {

    NO_USERS_REGISTERED (ERRO, "msg.emessage.no.users.registered"),
    NO_GET_ROOM_INFORMATION (ERRO, "msg.emessage.no.get.room.information"),
    NO_ENTER_ROOM (ERRO, "msg.emessage.no.enter.room"),
    NO_CHANGE_ROOM_HOST (ERRO, "msg.emessage.no.change.room.host"),
    NO_LEAVE_ROOM (ERRO, "msg.emessage.no.leave.room"),
    NO_SAVE_USER (ERRO, "msg.emessage.no.save.user"),
    NO_RETURN_USER (ERRO, "msg.emessage.no.return.user"),
    NOT_WRITE_VALUE_JSON (ERRO, "msg.emessage.not.write.value.json");

    private String message;
    private ELevelMessage level;

    EMessage(ELevelMessage level, String message){
        this.level = level;
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
