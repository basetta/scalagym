package org.pupazzo.scalagym.mail

import java.util.Properties
import javax.mail.{Flags, Folder, Session}

object DeleteImapMessages extends App {
    
    // Create empty properties
    val props = new Properties();

    // Get session
    val session = Session.getDefaultInstance(props, null);

    // Get the store
    val store = session.getStore("imap");
    store.connect( EmarsysCredentials.host, EmarsysCredentials.userName, EmarsysCredentials.password);

    // Get folder
    val folder = store.getFolder("INBOX")
    folder.open( Folder.READ_WRITE )


    // Get directory
    val message = folder.getMessages()

    message.foreach{ m =>

       println( "Deleting Message " + m.getSubject )
       m.setFlag( Flags.Flag.DELETED, true )

    }

    // close the folder, passing in a true value to expunge the deleted messages.
    folder.close( true );
    store.close( );
}