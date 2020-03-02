    private class JoinHandler implements MessageHandler {
        private ChatNode peer;

        public JoinHandler(ChatNode peer)
        {
            this.peer = peer;
        }

		@Override
		public void handleMessage(Connection conn, Message msg) {

		}
    }