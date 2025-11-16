<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Ticket</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css">
    <style>
        body { margin-top: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Ticket</h1>
        <form action="tickets" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${ticket.id}">

            <label for="title">Title</label>
            <input type="text" id="title" name="title" class="u-full-width" value="${ticket.title}" required>

            <label for="description">Description</label>
            <textarea id="description" name="description" class="u-full-width" required>${ticket.description}</textarea>

            <label for="status">Status</label>
            <select id="status" name="status" class="u-full-width">
                <option value="open" ${ticket.status == 'open' ? 'selected' : ''}>Open</option>
                <option value="closed" ${ticket.status == 'closed' ? 'selected' : ''}>Closed</option>
            </select>

            <input type="submit" value="Update Ticket" class="button-primary">
            <a href="tickets" class="button">Cancel</a>
        </form>
    </div>
</body>
</html>