package com.dily.entities;

import org.springframework.social.facebook.api.*;

/**
 * Created by rusum on 02.06.2017.
 */
public interface Facebook extends GraphApi {

    CommentOperations commentOperations();

    EventOperations eventOperations();

    FeedOperations feedOperations();

    FriendOperations friendOperations();

    GroupOperations groupOperations();

    LikeOperations likeOperations();

    MediaOperations mediaOperations();

    //PlacesOperations placesOperations();

    UserOperations userOperations();

}
