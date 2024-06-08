package com.capgemini.wsb.fitnesstracker.mail.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;

import java.util.List;

/**
 * API interface for component responsible for sending emails.
 */
public interface EmailSender {

    /**
     * Sends the email message to the recipient from the provided {@link EmailDto}.
     *
     * @param email information on email to be sent
     */
    void send(EmailDto email);

    /**
     * LAB3: Sends email report
     * @param user user object
     * @param trainingList trainings list
     */
    void emailReport(final String user, final List<Training> trainingList);

}
